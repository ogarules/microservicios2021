import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { OktaAuthService } from "@okta/okta-angular";
import { from, Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private oktaAuthService: OktaAuthService) {
        
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return from(this.handleAccess(req, next));
    }
    
    handleAccess(req: HttpRequest<any>, next: HttpHandler) {
        console.log('Inyectando token');

        const allowedOrigins = ['http://localhost', 'https://my.humanresources.com', 'http://my.humanresources.com'];
        
        if (allowedOrigins.some(url => req.urlWithParams.includes(url))) {
            const accessToken = this.oktaAuthService.getAccessToken();
            console.log(accessToken);

            req = req.clone({
                setHeaders: {
                    Authorization: 'Bearer ' + accessToken
                }
            });
        }

        return next.handle(req).toPromise();
    }

}