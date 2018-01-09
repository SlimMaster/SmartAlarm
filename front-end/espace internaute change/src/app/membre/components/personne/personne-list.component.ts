import {Component, OnDestroy, OnInit, ElementRef, AfterViewInit} from '@angular/core';

import {ActivatedRoute, Router} from '@angular/router';
import {PersonneAutoriseeService} from "../../../_services/personne_autorisee.service";
import {PersonneAutorisee} from "../../../_models/personneAutorisee";
import {Subscription} from "rxjs/Subscription";
import {PubSubService} from "../../../_services/pubSubService.service";
import {UserService} from "../../../_services/user.service";
import {User} from "../../../_models/user";
import {error} from "selenium-webdriver";
import {fadeInAnimation} from "../../../_animations/fade-in.animation";
import {Element} from "@angular/compiler";

declare var $:any;
@Component({
  selector: 'app-personne',
  templateUrl: './personne-list.component.html',
  styleUrls: ['./personne-list.component.css'],

 // animations: [fadeInAnimation],
  // host: { '[@fadeInAnimation]': '' }
})
export class PersonneListComponent implements OnInit, OnDestroy ,AfterViewInit{


  private personne_autorisee : PersonneAutorisee[];
  private subscription: Subscription;
  personne: any = {};
  currentUserCookies : User;
  currentUserBody : User;

  constructor(private elt:ElementRef,
    private route: ActivatedRoute,
    private personne_service : PersonneAutoriseeService,

    private router : Router,
    private pubSubService: PubSubService,
    private userService: UserService
  )
  {
    this.currentUserCookies = JSON.parse(localStorage.getItem('currentUser'));
  }

   routeMe(){
     this.router.navigate(['addmember']);
     console.log('route me');
   }
  ngAfterViewInit() {
    $('.button').click(function(){
      var buttonId = $(this).attr('id');
      $('#modal-container').removeAttr('class').addClass(buttonId);
      $('body').addClass('modal-active');
    })

    $('#modal-container').click(function(){
      $(this).addClass('out');
      $('body').removeClass('modal-active');
    });


  }

  ngOnInit() {
     this.loadPersonnes();
    // reload persons when updated
    this.subscription = this.pubSubService.on('personne-updated').subscribe(() => this.loadPersonnes());

    //this.notifService.getNotifications().subscribe(notifs => this.notifications = notifs);
  }
  ngOnDestroy() {
    // unsubscribe to ensure no memory leaks
    this.subscription.unsubscribe();
    }
  private loadPersonnes() {
    console.log(" GO GO GO");
    this.loadCurrentUserInformation(this.currentUserCookies.username);
    //

    /*this.personne_service.getAll().subscribe(
      people => {
        console.log(people);
        this.personne_autorisee = people;
      }
    );*/
  }


  delete(id:number){
     console.log("delete goo for id :"+id);

     this.personne_service.delete(id).subscribe(
       message => {
         // redirect to users view
         console.log(message);
         this.router.navigate(['home/personne']);

         // publish event so list controller can refresh
         this.pubSubService.publish('personne-updated');

       },
       error => {
         console.log("holla ERRRROR");
       }
     );
  }



  loadCurrentUserInformation(username) {
    console.log(username);
    this.userService.getByUsername(username).subscribe(
      data =>{
        console.log("salut");
        this.currentUserBody = data;
        console.log("salut");
        console.log(this.currentUserBody);
        this.personne_autorisee = this.currentUserBody.personnes;
        console.log(this.personne_autorisee);
      },
      error =>{
        console.log("erreur");
      }
    );


  }


}
