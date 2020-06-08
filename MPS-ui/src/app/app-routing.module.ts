import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SubstListComponent} from "./subst-list/subst-list.component";
import {AppComponent} from "./app.component";
import {RecentComponent} from "./recent/recent.component";
import {ByIdComponent} from "./by-id/by-id.component";


const routes: Routes = [
  {path:'rest/select', component : SubstListComponent},
  {path:'rest/recent', component : RecentComponent},
  {path:'rest/:id', component : ByIdComponent}//,
  //{path: '**', component : AppComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
