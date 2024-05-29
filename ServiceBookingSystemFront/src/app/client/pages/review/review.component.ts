import { Component } from '@angular/core';
import { ClientService } from '../../services/client.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { error } from 'console';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.scss'
})
export class ReviewComponent {

  bookId:number = this.activatedRoute.snapshot.params['Id'];
  validateForm!: FormGroup;

  constructor(private clientService: ClientService,
              private notification: NzNotificationService,
              private router: Router,
              private fb: FormBuilder,
              private activatedRoute: ActivatedRoute){}

  ngOnInit(){
    this.validateForm = this.fb.group({
      rating:[null, Validators.required],
      review:[null,Validators.required],
    })
  }

  giveReview(){
    const reviewDto = {
      rating: this.validateForm.get("rating").value,
      review: this.validateForm.get("review").value,
      userId: UserStorageService.getUserId(),
      bookId: this.bookId
    }
    this.clientService.giveReview(reviewDto).subscribe(res=>{
      this.notification
      .success(
        'SUCCESS',
        'Review posted succesfully',
        {nzDuration:5000}
      )
      this.router.navigateByUrl("/client/bookings")
    },error=>{
      this.notification
      .error(
        'ERROR',
        `${error.message}`,
        {nzDuration:5000}
      )

    })
  }

}
