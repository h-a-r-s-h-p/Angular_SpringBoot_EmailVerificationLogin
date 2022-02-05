import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {

  title = 'Verification';
  emailAddr : string;
  OTP : string="";
  isShown : boolean = false;

  constructor(private userService:UserServiceService,
              private route: ActivatedRoute,
              private router: Router) {}
  
  ngOnInit(): void {
    this.emailAddr = this.route.snapshot.params['email'];
  }

  onSubmit(){
    this.isShown = false;
    this.userService.verifyOTP(this.OTP, this.emailAddr).subscribe(
      data=> {
        if(data){
          this.userService.userLoggedIn(this.emailAddr).subscribe(
              data => {
                if(data === false) {
                  this.saveUser();
                  this.router.navigate(['/acknowledge']);
                }
                else this.router.navigate(['/error',this.emailAddr]);
              }
          )
        } 
        else{
          this.OTP="";
          this.isShown = true;
        }
      }
    )
  }


  saveUser(){
    this.userService.newUser(this.emailAddr).subscribe( 
      data => { 
      },
      error => console.log("error while creating user")
    );
  }
}
