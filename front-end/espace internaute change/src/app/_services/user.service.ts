import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../_models/index';

@Injectable()
export class UserService {

    userUrl = 'http://localhost:8080/users';
    registerUrl = '/register';
  constructor(private http: Http) { }


    getAll() {
        return this.http.get(this.userUrl, this.jwt()).map((response: Response) => response.json());
    }

    //getByEmailAndToken(String: email, String: token){
  //
  // }


  getByUsername(username: string) {
    console.log(username+'service');
    return this.http.get(this.userUrl + '/' + username, this.jwt()).map((response: Response) => response.json());
  }

    create(user: User) {
        console.log("create user");
        return this.http.post(this.userUrl+this.registerUrl, user, this.jwt()).map((response: Response) => response.json());
    }

    update(user: User) {
        return this.http.put(this.userUrl +'/'+ user.id, user, this.jwt()).map((response: Response) => response.json());
    }




    // private helper methods

    private jwt() {
        // create authorization header with jwt token
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            const headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token ,
            'Content-Type':'application/json'});
            return new RequestOptions({ headers: headers });
        }
        else {

          const headers = new Headers({
            'Content-Type':'application/json'});
          return new RequestOptions({ headers : headers });
        }
    }
}
