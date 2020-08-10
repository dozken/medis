import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/auth/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loginSubscription: Subscription;
  loggedIn: Boolean = false;

  constructor(private router: Router, private auth: AuthService) {}

  ngOnInit() {
    this.loggedIn = !this.auth.isTokenExpired();
    this.loginSubscription = this.auth.loginStatus.subscribe(status => {
      this.loggedIn = status.login;
    });
  }

  signOut(){
    this.auth.signOut();
    this.router.navigate(['login']);
  }
}
