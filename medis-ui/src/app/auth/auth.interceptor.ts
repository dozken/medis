import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router, RouterStateSnapshot } from '@angular/router';
import 'rxjs/add/operator/do';
import { AuthService } from './auth.service';

const TOKEN_HEADER_KEY = 'x-auth-token';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService, private router: Router) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = req;
    if (this.auth.getToken() != null) {
      authReq = req.clone({
        headers: req.headers.set(
          TOKEN_HEADER_KEY,
          // 'Bearer ' +
          this.auth.getToken()
        )
      });
    }
    return next.handle(authReq).do(
      () => {},
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401 || err.status === 403) {
            this.router.navigate(['login']);
          }
        }
      }
    );
  }
}
