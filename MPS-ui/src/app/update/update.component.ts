import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Subst} from "../subst-list/subst";
import {ActivatedRoute} from "@angular/router";
import {HttpService} from "../service/http.service";

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
  id : number;

  constructor(private httpClient:HttpClient, private httpService: HttpService,  private activatedRoute: ActivatedRoute){}

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id']
    this.httpService.getbyId(this.id).subscribe(
      data=>this.subst = data)
  }
  onSubmit(){
    this.httpClient.patch(
      'https://localhost:8443/rest/' + this.id,this.model,{
        headers: new HttpHeaders().set('Content-type','application/json'),
      }).subscribe(res => {
                    console.log('received ok response from patch request');
                  },
                  error => {
                    console.error('There was an error during the request');
                    console.log(error);
                  }
                  );
    console.log('request sent. Waiting for response...');



    /*this.HttpService.post(
      'http://localhost:8080/design',this.model,{
        headers: new HttpHeaders().set('Content-type','application/json'),
      }).subscribe(data=>this.subst=data)
      */
  }

}
