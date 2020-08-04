import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Subst} from "../subst-list/subst";

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
@Injectable()
export class UpdateComponent implements OnInit {

  model={
    nameSubst: '',
    ip: '',
    zone: ''
  }
  subst : Subst;

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
  }
  onSubmit(){
    /*this.httpClient.post(
      'http://localhost:8080/design',this.model,{
        headers: new HttpHeaders().set('Content-type','application/json'),
      }).subscribe(data=>this.subst=data)
      */
  }

}
