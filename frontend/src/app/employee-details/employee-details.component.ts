import { Category } from '../category';
import { Component, OnInit, Input } from '@angular/core';
import { CategoryService } from '../category.service';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  category: Category;

  constructor(private route: ActivatedRoute,private router: Router,
    private categoryService: CategoryService) { }

  ngOnInit() {
    this.category = new Category();

    this.id = this.route.snapshot.params['id'];

    this.categoryService.getEmployee(this.id)
      .subscribe(data => {
        console.log(data)
        this.category = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['/employees']);
  }
}
