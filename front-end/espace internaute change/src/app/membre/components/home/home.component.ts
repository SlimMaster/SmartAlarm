import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../_services/authentication.service";
import {Router} from "@angular/router";
import {fadeInAnimation} from "../../../_animations/fade-in.animation";


@Component({
    templateUrl: 'home.component.html',
  styleUrls:['./home.component.css'],
  // make fade in animation available to this component
  animations: [fadeInAnimation],

  // attach the fade in animation to the host (root) element of this component
  host: { '[@fadeInAnimation]': '' }
})

export class HomeComponent {
  //@Input() psw: string;
  //console.log(psw);
  shadow = "true";
  constructor(
    private router: Router,
    private authServ : AuthenticationService
  ) {}
  logout() {
    this.authServ.logout();
    this.shadow = "false";
    this.changeshadow();
  }
  changeshadow():string{
    return this.shadow;
  }
}
