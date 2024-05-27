import { Component } from '@angular/core';
import { CompanyService } from '../../../basic/services/company.service';

@Component({
  selector: 'app-company-dashboard',
  templateUrl: './company-dashboard.component.html',
  styleUrl: './company-dashboard.component.scss'
})
export class CompanyDashboardComponent {

  bookings:any;

  constructor(private companyService: CompanyService){}


  ngOnInit(){
    this.getAllAdBookings();
  }

  getAllAdBookings(){
    this.companyService.getAllAdBookings().subscribe(res=>{
      console.log(res);
      this.bookings = res;
    })
  }

}
