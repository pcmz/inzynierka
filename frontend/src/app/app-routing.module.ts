import { ProductDetailsComponent } from './product-details/product-details.component';
import { CreateProductComponent } from './create-product/create-product.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { OrderDetailsComponent } from './order_details/order_details.component';
import {MockDeliveryWebsiteComponent} from './mock-delivery-website/mock_delivery_website.component';

import { UpdateProductComponent } from './update-product/update-product.component';
import {LogoutComponent} from "./logout/logout.component";

const routes: Routes = [
  { path: '', redirectTo: 'product', pathMatch: 'full' },
  { path: 'products', component: ProductListComponent },
  { path: 'add', component: CreateProductComponent },
  { path: 'update/:id', component: UpdateProductComponent },
  { path: 'details/:id', component: ProductDetailsComponent },
  { path: 'carts', component: CartComponent },
  { path: 'carts/:id', component: CartComponent },
  { path: 'order_details/:id', component: OrderDetailsComponent },
  {path: 'mock_delivery_website/:id', component: MockDeliveryWebsiteComponent},
  { path: 'orders', component: OrderComponent },

  {
    path: 'logout',
    component:LogoutComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
