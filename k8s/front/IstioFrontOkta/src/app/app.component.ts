import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'IstioFrontOkta';
  user: any = {};

  constructor(public oktaAuthService: OktaAuthService, private router: Router, @Inject('AuthData') public authentication) { }

  async ngOnInit() {
    this.authentication.isAuthenticated = await this.oktaAuthService.isAuthenticated();

    this.oktaAuthService.$authenticationState.subscribe(
      (isAuth: boolean) => {
         this.authentication.isAuthenticated = isAuth;
        this.oktaAuthService.getUser().then((value) => this.user = value);
      }
    );

    this.user = await this.oktaAuthService.getUser();
  }
}
