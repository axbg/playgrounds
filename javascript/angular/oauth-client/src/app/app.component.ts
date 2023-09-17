import {Component, OnInit} from '@angular/core';
import {AppService} from './app.service'
 
@Component({
  selector: 'app-root',
  template: `<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Spring Security Oauth - Authorization Code</a>
    </div>
  </div>
</nav>
<router-outlet></router-outlet>`
})

export class AppComponent implements OnInit {
  constructor() { }  
   
  ngOnInit() {  
  }  
}