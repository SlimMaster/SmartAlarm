import { Component, OnInit } from '@angular/core';

import {Router} from "@angular/router";
import {UserService} from "../_services/user.service";
import {AlertService} from "../_services/alert.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  model: any = {};
  loading = false;
  constructor(

    private router: Router,
    private userService: UserService,
    private alertService: AlertService) { }



  ngOnInit() {
  }


  register() {
    this.loading = true;
    this.model.enabled = 0;
    this.model.lastPasswordResetDate = new Date();
    console.log(this.model);
    this.userService.create(this.model)
      .subscribe(
        data => {
          this.alertService.success('Compte créé avec Succès', true);
          this.router.navigate(['/connextion']);
        },
        error => {
          this.alertService.error('Cette adresse mail est déja utilisée !');
          this.loading = false;
        });
  }







}
