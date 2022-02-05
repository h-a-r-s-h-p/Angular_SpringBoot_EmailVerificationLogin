import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ObjectUnsubscribedError } from 'rxjs';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  constructor(private userService : UserServiceService,
              private route: ActivatedRoute,
              private router: Router) { }

  emailAddr : string;
  title = 'User already logged In!!!';
  ngOnInit(): void {
    this.emailAddr = this.route.snapshot.params['id'];
  }

  LogOut(){
    this.userService.deleteUser(this.emailAddr).subscribe(
      data => {}
    );
    this.router.navigate(['']);
  }

}
