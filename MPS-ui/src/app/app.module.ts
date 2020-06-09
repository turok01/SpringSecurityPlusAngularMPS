import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { SubstListComponent } from './subst-list/subst-list.component';
import { RecentComponent } from './recent/recent.component';
import { ByIdComponent } from './by-id/by-id.component';

@NgModule({
  declarations: [
    AppComponent,
    SubstListComponent,
    RecentComponent,
    ByIdComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }