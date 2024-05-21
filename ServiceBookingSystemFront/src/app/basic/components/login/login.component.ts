import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Route, Router } from '@angular/router';
import { error } from 'console';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private notification: NzNotificationService,
              private router: Router
  ){}

  ngOnInit(){
    this.validateForm = this.fb.group({
      username: [null,[Validators.required]],
      password:[null,[Validators.required]]
    })
  }

  submitForm(){
    this.authService.login(this.validateForm.get(['username'])!.value, 
                           this.validateForm.get(['password'])!.value)
    .subscribe(res=>{
      console.log(res);
      if(UserStorageService.isClientLogged){
        this.router.navigateByUrl('client/dashboard')
      }else if(UserStorageService.isCompanyLogged){
        this.router.navigateByUrl('company/dashboard')
      }
    }, error=>{
      this.notification
      .error(
      'ERROR',
      'Bad credentials',
      {nzDuration: 5000}
      )
    })
  }

}
