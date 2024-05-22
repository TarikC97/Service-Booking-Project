import { Injectable } from '@angular/core';

const TOKEN = 's_token';
const USER = 's_user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token: string):void{

    localStorage.removeItem(TOKEN);
    localStorage.setItem(TOKEN, token);     
  }


  static getToken(): string{
    return typeof window !== 'undefined'&&localStorage.getItem(TOKEN);
  }
//typeof window !== 'undefined'&&
//typeof window !== 'undefined'?...:undefined;
  public saveUser(user:any):void{
      localStorage.removeItem(USER);
      localStorage.setItem(USER, JSON.stringify(user)); 
  }
  static getUser(): any{
    return typeof window !== 'undefined'&&JSON.parse(localStorage.getItem(USER));
  }

  static getUserId():string{
    const user = this.getUser();
    if(user === null){return '';}
    return user.userId;
  }

  static getUserRole():string{
    const user = this.getUser();
    if(user === null){return '';}
    return typeof window !== 'undefined'&&user.role;
  }

  static isClientLogged():boolean{
    if(this.getToken() === null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'CLIENT';
  }
  static isCompanyLogged():boolean{
    if(this.getToken() === null){
      return false;
    }
    const role: string = this.getUserRole();
    return role == 'COMPANY';
  }
  static signOut():void{
   localStorage.removeItem(TOKEN);
   localStorage.removeItem(USER);
  }
}
