import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseURL  = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  
  constructor(private httpClient: HttpClient) { }


  newUser(emailAddr : string): Observable<any>{
    return this.httpClient.post(`${baseURL}/user` , emailAddr);
  }

  userLoggedIn(email : string) : Observable<any>{
    return this.httpClient.get(`${baseURL}/${email}`);
  }
 
  deleteUser(id: string) : Observable<any>{
    return this.httpClient.delete(`${baseURL}/deleteduser/${id}`);
  }

  sendMail(mailID: string) : Observable<any>{
    return this.httpClient.get(`${baseURL}/mail/${mailID}`);
  }

  verifyOTP(OTP : string, emailAddr: string) : Observable<any>{
    return this.httpClient.post(`${baseURL}/verify/${emailAddr}`, OTP);
  }
}
