import { Component } from '@angular/core';
import { CompanyService } from '../../../basic/services/company.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-ad',
  templateUrl: './update-ad.component.html',
  styleUrl: './update-ad.component.scss'
})
export class UpdateAdComponent {

  adId: any = this.activatedRoute.snapshot.params['id'];

  constructor(private companyService: CompanyService,
              private activatedRoute: ActivatedRoute
  ){}

  ngOnInit(){
    this.getAdById();
  }

  getAdById(){
    this.companyService.getAdById(this.adId).subscribe(res=>{
      console.log(res);
    })
  }

}
 