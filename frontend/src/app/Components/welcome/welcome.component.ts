import { Component, OnInit } from '@angular/core';
import { UserServiceService } from 'src/app/service/user-service.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  emailAddr : string;
  constructor(private userService: UserServiceService, private router: Router ) { }

  get getEmail(){
    return this.userEmails.get('email')
  }

  userEmails = new FormGroup(
    {
      email : new FormControl('',[
        Validators.required,
        Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
      ])
    }
  );

  isShown : boolean = false;


  ngOnInit(): void {
  }



  onSubmit(){
    this.isShown=false;
    this.userService.sendMail(this.emailAddr).subscribe(
      data => {
        if(data === 1)
        this.router.navigate(['/verify',this.emailAddr]);
        else{
          this.isShown= true;
          setTimeout(() => {
            this.isShown = false;
          }, 3000);

          this.emailAddr="";
        }
    })
 
  }
}
