import {Injectable} from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { JwtHelperService } from "@auth0/angular-jwt";
 
export class Foo {
  constructor(
    public id: number,
    public name: string) { }
} 

@Injectable()
export class AppService {
   public jwtHelper = new JwtHelperService();
   public clientId = 'angular-app';
   public redirectUri = 'http://localhost:8084/';

  constructor(private _http: HttpClient){}

  retrieveToken(code){
    const params = new URLSearchParams();   
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('redirect_uri', this.redirectUri);
    params.append('code',code);
    params.append('code_verifier',window.localStorage.getItem("verifier"));

    const headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'});
     this._http.post('http://localhost:8080/realms/boot/protocol/openid-connect/token', params.toString(), { headers: headers })
    .subscribe(
      data => this.saveToken(data),
      err => alert('Invalid Credentials')
    ); 
  }

  saveToken(token){
    const expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    console.log('Obtained Access token');
    window.location.href = 'http://localhost:8084';
  }

  getResource(resourceUrl) : Observable<any>{
    const headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer ' + Cookie.get('access_token')});
    return this._http.get(resourceUrl, { headers: headers, responseType: 'text' });
  }

  checkCredentials(){
    return Cookie.check('access_token');
  } 

  logout() {
    Cookie.delete('access_token');
    window.location.reload();
  }
}