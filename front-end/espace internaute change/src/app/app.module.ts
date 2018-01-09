import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule ,JsonpModule } from '@angular/http';

//router module
import {RouterModule} from '@angular/router';

//adding hammerjs for slide toggle
import 'hammerjs';


//angular material components
import {MdButtonModule} from '@angular2-material/button';
import {MdIconModule} from '@angular2-material/icon';
import {MdIconRegistry} from '@angular2-material/icon';
import {MdCardModule} from '@angular2-material/card';
import {MdTabsModule} from '@angular2-material/tabs';
import {MdInputModule} from '@angular2-material/input';
import {MdSliderModule} from '@angular2-material/slider';
import {MdListModule} from '@angular2-material/list';
import {MdToolbarModule} from '@angular2-material/toolbar';
import {MdRadioModule} from '@angular2-material/radio';



import { AppComponent } from './app.component';
import { HomeImageComponent } from './home-image/home-image.component';
import { HomeBodyComponent } from './home-body/home-body.component';
// import { HomeComponent } from './accueil/accueil.component';


import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';


import {AlertComponent} from "./_directives/alert.component";


import {AuthGuard} from "./_guards/auth.guard";
import {AlertService} from "./_services/alert.service";
import {AuthenticationService} from "./_services/authentication.service";
import {UserService} from "./_services/user.service";
import {ProfileComponent} from "./membre/components/profile/profile.component";


import {PersonneListComponent} from "./membre/components/personne/personne-list.component";

import {HomeComponent} from "./membre/components/home/home.component";
import { AccueilComponent} from "./accueil/accueil.component";
import {PersonneAutoriseeService} from "./_services/personne_autorisee.service";
import {PersonneAddEditComponent} from "./membre/components/personne/personne-add-edit.component";
import {PubSubService} from "./_services/pubSubService.service";
import {CommonModule} from "@angular/common";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
//--------------




@NgModule({
  declarations: [
    AppComponent,

    HomeImageComponent,
    HomeBodyComponent,
   // HomeComponent,


    SignInComponent,
    SignUpComponent,

    ///////---------/////////////////
    AlertComponent,


    ProfileComponent,


    //AdminComponent,
    PersonneAddEditComponent,
    PersonneListComponent,


    HomeComponent,
    AccueilComponent
  ],
  imports: [
    BrowserAnimationsModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    MdIconModule,
    MdButtonModule,
    MdCardModule,
    MdToolbarModule,
    JsonpModule,
    MdTabsModule,
    MdRadioModule,
    MdInputModule,
    MdSliderModule,
   // Browser
    MdListModule,


    RouterModule.forRoot([



      { path: 'home', component: HomeComponent, canActivate: [AuthGuard], children:[
        {path:'profile',component:ProfileComponent, canActivate: [AuthGuard]},

        {path:'personne',component:PersonneListComponent, canActivate: [AuthGuard], children:
        [
          {path:'edit/:id',component:PersonneAddEditComponent, canActivate: [AuthGuard]},
          {path: 'add', component: PersonneAddEditComponent,canActivate: [AuthGuard] }
        ]
        }


      ] },
      {path:'connextion',component:SignInComponent},

      {path:'',component:AccueilComponent},

      {path:'inscription',component:SignUpComponent},



      // otherwise redirect to accueil
      // { path: '**', redirectTo: '' }


    ])
  ],
  providers: [
    MdIconRegistry,
    AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,

    PersonneAutoriseeService,
    PubSubService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {


}
