import {Component} from '@angular/core';
import {AppService} from './app.service'
import { encode as base64encode } from "base64-arraybuffer";

 
@Component({
    selector: 'home-header',
    providers: [AppService],
  template: `<div class="container" >
    <button *ngIf="!isLoggedIn" class="btn btn-primary" (click)="login()" type="submit">Login</button>
    <div *ngIf="isLoggedIn" class="content">
        <span>Welcome !!</span>
        <a class="btn btn-default pull-right"(click)="logout()" href="#">Logout</a>
        <br/>
        <foo-details></foo-details>
    </div>
</div>`
})
 
export class HomeComponent {
    public isLoggedIn = false;

    private code_verifier = "";

    constructor(
        private _service:AppService){}
 
    ngOnInit(){
        this.isLoggedIn = this._service.checkCredentials();    
        let i = window.location.href.indexOf('code');
        if(!this.isLoggedIn && i != -1){
            this._service.retrieveToken(window.location.href.substring(i + 5));
        }
    }

    async login() {
        this.code_verifier = this.generateRandomString(128)
        window.localStorage.setItem("verifier", this.code_verifier);

        window.location.href = 'http://localhost:8080/realms/boot/protocol/openid-connect/auth?response_type=code&redirect_uri=' + 
        this._service.redirectUri + '&scope=profile%20email&client_id=' + 
          this._service.clientId;
          + '&code_challenge=' + await this.generateCodeChallenge()
          + '&code_challenge_method=S256';
    }
 
    logout() {
        this._service.logout();
    }

    // As described in https://www.valentinog.com/blog/challenge/
    async generateCodeChallenge() {
        const encoder = new TextEncoder();
        const data = encoder.encode(this.code_verifier);
        const digest = await window.crypto.subtle.digest("SHA-256", data);
        const base64Digest = base64encode(digest);
        
        return base64Digest
          .replace(/\+/g, "-")
          .replace(/\//g, "_")
          .replace(/=/g, "");
      }

    generateRandomString(length) {
        const charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        let randomString = "";
        for (let i = 0; i < length; i++) {
          const randomIndex = Math.floor(Math.random() * charset.length);
          randomString += charset.charAt(randomIndex);
        }
        return randomString;
      }
}