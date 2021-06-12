import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { Router, RouterModule, Routes } from "@angular/router";
import { OktaAuthModule, OktaCallbackComponent, OKTA_CONFIG } from "@okta/okta-angular";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { AuthInterceptor } from "./auth-interceptor";
import { AuthGuard } from "./AuthGuard";

const oktaConfig = {
    issuer: 'https://dev-909112.okta.com/oauth2/default',
    redirectUri: window.location.origin + '/callback',
    clientId: '0oa9zo6fjpSLAJtGq357',
    scopes: ['openid', 'profile']
}

let authData = {
    isAuthenticated:false
};

const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'callback', component: OktaCallbackComponent}
];

@NgModule({
    declarations: [
        HomeComponent
    ],
    imports: [
        CommonModule,
        HttpClientModule,
        OktaAuthModule,
        RouterModule.forRoot(routes)
    ],
    providers: [
        { provide: 'AuthData', useValue: authData },
        { provide: OKTA_CONFIG, useValue: oktaConfig },
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
    ]
})
export class AuthRoutingModule { }