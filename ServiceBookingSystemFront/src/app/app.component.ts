import { Component } from '@angular/core';
import { UserStorageService } from './basic/services/storage/user-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ServiceBookingSystemFront';

   isClientLoggedIn: boolean = UserStorageService.isClientLogged();
   isCompanyLoggedIn: boolean = UserStorageService.isCompanyLogged();

  constructor(private router: Router){}

  ngOnInit(){
    this.router.events.subscribe(event=>{
      this.isClientLoggedIn = UserStorageService.isClientLogged();
      this.isCompanyLoggedIn = UserStorageService.isCompanyLogged();
    })
  }

  logout(){
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
