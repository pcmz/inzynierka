import { Product } from '../product';
import { Cart } from '../cart';
import { Component, OnInit, Input } from '@angular/core';
import { ProductService } from '../product.service';
import { CartService } from '../cart.service';
import { ProductListComponent } from '../product-list/product-list.component';
import { Router, ActivatedRoute } from '@angular/router';

import { Observable, of } from "rxjs";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  id: number;
  product: Product;

  //cart
  count: number;
  oldQuantiy: number;
  carts: Cart = new Cart();

  constructor(private route: ActivatedRoute, private router: Router,
    private productService: ProductService, private cartService: CartService) { }

  ngOnInit() {
    this.product = new Product();

    this.id = this.route.snapshot.params['id'];

    this.productService.getProduct(this.id)
      .subscribe(data => {
        console.log(data)
        this.product = data;
      }, error => console.log(error));

    //cart
    this.count = 1;

  }

  list(){
    this.router.navigate(['/products']);
  }

  //cart
  addToCart() {
    this.oldQuantiy = this.product.quantity;
    this.product.quantity = this.oldQuantiy - this.count;
    this.productService.updateProduct(this.id, this.product)
      .subscribe(data => console.log(data), error => console.log(error));
    this.save();

    this.product = new Product();
    this.gotoList();
    // console.log('mama');
  }

  gotoList() {
    this.router.navigate(['/products']);
  }

  des: string;
  isVisible: boolean = false;
  validateCount() {
    console.log('Validate');
    const max = this.product.quantity;

    if(this.count > max || this.count < 1){
        this.showAlert();
    }
    if (this.count > max) {
      this.count = max;
      this.des = 'max stock is: ' + this.count;
    } else if (this.count < 1) {
      this.count = 1;
      this.des = 'min stock is: ' + this.count;
    }


  }

  showAlert() : void {
    if (this.isVisible) { // if the alert is visible return
      return;
    }
    this.isVisible = true;
    setTimeout(()=> this.isVisible = false, 2000); // hide the alert after 2.5s
  }

  save() {
    this.carts.product = this.product;
    this.carts.quantity = this.count;
    this.carts.subtotal = this.carts.product.unitPrice * this.carts.quantity;
    this.cartService.createCarts(this.carts)
      .subscribe(data => console.log(data), error => console.log(error));
    this.carts = new Cart();
  }

}
