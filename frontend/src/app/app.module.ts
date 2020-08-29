import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {APP_INITIALIZER, NgModule} from "@angular/core";
import {ReactiveFormsModule} from "@angular/forms";
import {NgxSpinnerModule} from "ngx-spinner";
import {TokenInterceptor} from "./interceptors/token-interceptor";
import {KeycloakService} from "./keycloak/keycloak.service";

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CreateProductComponent } from './create-product/create-product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductListComponent } from './product-list/product-list.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { OrderDetailsComponent } from './order_details/order_details.component';
import { CustomerComponent } from './customer/customer.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import {LogoutComponent} from "./logout/logout.component";

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';

import {CdkDetailRowDirective} from './product-list/cdk-detail-row.directive';
import { CdkTableModule } from '@angular/cdk/table';


export function kcFactory(keycloakService: KeycloakService) {
  return () => keycloakService.init();
}

@NgModule({
  declarations: [
    AppComponent,
    CreateProductComponent,
    ProductDetailsComponent,
    ProductListComponent,
    UpdateProductComponent,
    LogoutComponent,
    CdkDetailRowDirective,
    CartComponent,
    OrderComponent,
    OrderDetailsComponent,
    CustomerComponent

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgxSpinnerModule,
    FormsModule,
    NgbModule,
    CommonModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatIconModule,
    RouterModule,
    CdkTableModule


  ],
  providers: [
    KeycloakService,
    {
      provide: APP_INITIALIZER,
      useFactory: kcFactory,
      deps: [KeycloakService],
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
