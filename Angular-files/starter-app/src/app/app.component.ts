import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  username:String ="";
  age:number=0;
  title = 'welcome to Angular';
  desc:String ="Hi team,welcome to our angular hands on session"

  save(){
    console.log("the user's name is" +
    this.username +"whose age is"+this.age);
  }
}
