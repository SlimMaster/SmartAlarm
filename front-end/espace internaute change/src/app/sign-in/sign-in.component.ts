import { Component, OnInit } from '@angular/core';
import  {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from "../_services/alert.service";
import {AuthenticationService} from "../_services/authentication.service";



class AuthResponce{
  res:string;
}



@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  model: any = {};
  loading = false;
  returnUrl: string;

  private alias:string;
  private passwd:string;
  private errMessage : string;
  private successMessage : string;

  constructor(
   // private router : Router,
   // //////::----------///////:
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService) { }





  ngOnInit() {

     // reset login status
     this.authenticationService.logout();

     // get return url from route parameters or default to '/'
     this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/accueil';


  }

redirect(id:string){

}

private ac : AuthResponce;



  login() {
    this.loading = true;
    console.log(this.model.username);
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        data => {
          this.router.navigate(['/home']);
          this.errMessage = '';
          this.successMessage = 'Authentification reussite, connextion en cour ..';

        },
        error => {
          this.errMessage = 'Verifier votre alias ou votre mot de passe!';
          this.alertService.error('Nom ou/et Mot de passe incorrect');
          this.loading = false;
        });
  }



  redirectTo(){
    this.router.navigate(['/inscription']);
  }
}
