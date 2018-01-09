import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {PubSubService} from "../../../_services/pubSubService.service";
import {PersonneAutoriseeService} from "../../../_services/personne_autorisee.service";
import {slideInOutAnimation} from "../../../_animations/slide-in-out.animation";
import {User} from "../../../_models/user";
import {UserService} from "../../../_services/user.service";
import {PersonneAutorisee} from "../../../_models/personneAutorisee";
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  templateUrl: 'personne-add-edit.component.html',
  // make slide in/out animation available to this component
  //animations: [slideInOutAnimation],

  // attach the slide in/out animation to the host (root) element of this component
 // host: { '[@slideInOutAnimation]': '' }
})


export class PersonneAddEditComponent implements OnInit {

  title = "Ajouter une personne";
  choixBouton = "Enregistrer";
  private personneCiblee : any = {};
  currentUserCookies : User;
  currentUserBody : User;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private personneService: PersonneAutoriseeService,
    private pubSubService: PubSubService,
    private userService: UserService ) {

    this.currentUserCookies = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    let personneId = Number(this.route.snapshot.params['id']);
    if (personneId) {
      this.title = 'Modifier les informations';
      this.choixBouton = 'Enregistrer les modifications';
      this.personneService.getById(personneId).subscribe(
        person => {
          this.personneCiblee = person;
        //  console.log(this.personneCiblee);
         // console.log(person);

        },
        error =>{
          console.log("errrrrrrror");
          console.log(error);

        }
      );

    }
  }

  loadPerson(id:number) {

  }
  saveUpdatePersonne() {
    if (this.title == "Ajouter une personne" ) {
      this.personneService.create(this.personneCiblee).subscribe(
        () => {
          // redirect to users view
          this.router.navigate(['home/personne']);

          // publish event so list controller can refresh
          this.pubSubService.publish('personne-updated');
        }
      );
    } else {
      this.personneService.update(this.personneCiblee).subscribe(
        () => {
          // redirect to users view
          this.router.navigate(['home/personne']);

          // publish event so list controller can refresh
          this.pubSubService.publish('personne-updated');
        }
      );
    }

  }

}
