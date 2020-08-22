import { ProductDetailsComponent } from './../product-details/product-details.component';

import { Observable, of } from "rxjs";
import { map, filter, startWith } from 'rxjs/operators';

import { ProductService } from "./../product.service";
import { Product } from "./../product";

import { Component, OnInit, ViewChild, PipeTransform, ChangeDetectorRef } from "@angular/core";
import { Router } from '@angular/router';

import { DecimalPipe } from '@angular/common';
import { FormControl } from '@angular/forms';

import { Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';

import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table';

import {animate, state, style, transition, trigger} from '@angular/animations';


@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.css"],
  providers: [DecimalPipe],
  animations: [
    trigger('detailExpand', [
      state('void', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('*', style({ height: '*', visibility: 'visible' })),
      transition('void <=> *', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ProductListComponent implements OnInit {
  products: Observable<Product[]>;

  brokers: Product[] = [];

  productsArray: Product[] = [];

  displayedColumns = ['id', 'productName', 'unit', 'quantityPerUnit', 'unitPrice', 'quantity', 'actionsColumn'];
  dataSource: MatTableDataSource<Product>;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  constructor(private productService: ProductService, pipe: DecimalPipe, private router: Router) {
    this.productService.getProductsList().subscribe((productsArray) => {
      this.productsArray = productsArray as Product[]
      this.dataSource = new MatTableDataSource(this.productsArray);
      console.log(this.productsArray);
      console.log(this.productsArray.length);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.products = this.productService.getProductsList();
  }

  deleteProduct(id: number) {
    // const dsData = this.dataSource.data;
    this.productService.deleteProduct(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
          this.deleteRowDataTable (id, this.idColumn, this.paginator, this.dataSource);
        },
        error => console.log(error));
  }

// ------------------------------------------
private dsData: any;
private idColumn = 'id';

// Remove the deleted row from the data table. Need to remove from the downloaded data first.
  private deleteRowDataTable (recordId, idColumn, paginator, dataSource) {
    this.dsData = dataSource.data;
    const itemIndex = this.dsData.findIndex(obj => obj[idColumn] === recordId);
    dataSource.data.splice(itemIndex, 1);
    dataSource.paginator = paginator;
  }

  productDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateProduct(id: number){
    this.router.navigate(['update', id]);
  }

      applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
        this.dataSource.filter = filterValue;
      }

      isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

}
