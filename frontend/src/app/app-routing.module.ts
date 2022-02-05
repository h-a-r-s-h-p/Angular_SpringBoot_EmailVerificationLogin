import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './Components/error/error.component';
import { ThankYouComponent } from './Components/thank-you/thank-you.component';
import { VerificationComponent } from './Components/verification/verification.component';
import { WelcomeComponent } from './Components/welcome/welcome.component';

const routes: Routes = 
[
  {
    path : '', component : WelcomeComponent
  },
  {
    path : 'verify/:email', component : VerificationComponent
  },

  {
    path : 'acknowledge', component : ThankYouComponent
  },

  {
    path : 'error/:id', component : ErrorComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
