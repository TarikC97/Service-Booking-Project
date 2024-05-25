import { Component } from '@angular/core';
import { CompanyService } from '../../../basic/services/company.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.scss'
})
export class AllAdsComponent {

  ads:any;

  constructor(private companyService: CompanyService,
              private notification: NzNotificationService
  ){}

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
    deleteAd(adId:any){
      this.companyService.deleteAd(adId).subscribe(res=>{
        this.notification
            .success(
                'SUCCESS',
                'Ad Deleted Succesfully',
                {nzDuration: 5000}
            );
            this.getAllAds();
      });
    }
}
