import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './home.component';
import { FooComponent } from './foo.component';
import {AppService} from './app.service'

// As described in https://github.com/Baeldung/spring-security-oauth/tree/master/oauth-jwt/oauth-ui-authorization-code-angular-jwt/src/main/resources
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooComponent    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
     { path: '', component: HomeComponent, pathMatch: 'full' }], {onSameUrlNavigation: 'reload'})
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
