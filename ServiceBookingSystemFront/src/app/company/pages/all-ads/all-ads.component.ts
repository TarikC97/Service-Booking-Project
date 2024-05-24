import { Component } from '@angular/core';
import { CompanyService } from '../../../basic/services/company.service';

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.scss'
})
export class AllAdsComponent {

  ads:any;

  constructor(private companyService: CompanyService){}

  ngOnInit(){
    this.getAllAds();
    console.log('Test')
  }

  getAllAds(){
    this.companyService.getAds().subscribe(res =>{
      this.ads =res;
    })
  }
  updateImg(img) {
    return 'data:image/jpeg;base64,'+img;
    }
}
