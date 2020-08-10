import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from './../../auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;                    // {1}
  private formSubmitAttempt: boolean; // {2}
  returnUrl: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,         // {3}
    private authService: AuthService // {4}
  ) { }

  ngOnInit() {
    this.form = this.fb.group({     // {5}
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

  }

  isFieldInvalid(field: string) { // {6}
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.login(this.form.value).subscribe(
        () => {
          const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
          this.router.navigate([returnUrl]);
          // this.router.navigate(['/']);
        },
        (error) => {
          console.log(error);
        }
      );
    }
    this.formSubmitAttempt = true;             // {8}
  }
}
