import { ProductDetailsComponent } from './product-details/product-details.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import {LogoutComponent} from "./logout/logout.component";

const routes: Routes = [
  { path: '', redirectTo: 'product', pathMatch: 'full' },
  { path: 'products', component: ProductListComponent },
  { path: 'add', component: CreateProductComponent },
  { path: 'update/:id', component: UpdateProductComponent },
  { path: 'details/:id', component: ProductDetailsComponent },
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
