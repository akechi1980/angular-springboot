import { Component } from '@angular/core';
import { UsersService } from '../user-list/users.service';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html'
})
export class CompanyListComponent {

  data: any = {};

  constructor(private mUsersService: UsersService) { }

  ngOnInit(): void {
    this.mUsersService.getData().subscribe(data => {
      console.log(data);
      this.data = data;
    });
  }

}
