import {PersonneAutorisee} from "./personneAutorisee";
export class User {
    id: number;
    username: string;
    password: string;
    nom: string;
    prenom: string;
    email: string;
    enabled: number;
    lastPasswordResetDate: Date; //new Date("February 4, 2016 10:13:00")
    personnes : PersonneAutorisee[];
}
