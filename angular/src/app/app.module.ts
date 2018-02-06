import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './/app-routing.module';
import {ItemComponent} from './item/item.component';
import {HomeComponent} from './home/home.component';
import {ItemService} from './services/item.service';

import * as FusionCharts from 'fusioncharts';
import * as Charts from 'fusioncharts/fusioncharts.charts';
import * as FintTheme from 'fusioncharts/themes/fusioncharts.theme.fint';
import {FusionChartsModule} from 'angular4-fusioncharts';
import { FormatNumberPipe } from './pipes/format-number.pipe';

FusionChartsModule.fcRoot(FusionCharts, Charts, FintTheme);

@NgModule({
  declarations: [
    AppComponent,
    ItemComponent,
    HomeComponent,
    FormatNumberPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule,
    FusionChartsModule
  ],
  providers: [ItemService],
  bootstrap: [AppComponent]
})
export class AppModule {}
