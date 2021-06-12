import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthService } from '@okta/okta-angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private oktaAuthService: OktaAuthService, private router : Router, @Inject('AuthData') private authentication) { }

  async ngOnInit() {
    let isAuthenticated = await this.oktaAuthService.isAuthenticated();

    this.oktaAuthService.$authenticationState.subscribe(
      (isAuth: boolean) => {
         this.authentication.isAuthenticated = isAuth;
        if (isAuth) {
          this.router.navigate(['/home']);
        }
      }
    );

    if (!isAuthenticated) {
      this.oktaAuthService.signInWithRedirect({});
    }

    if (isAuthenticated) {
      this.router.navigate(['/home']);
    }
  }

}
