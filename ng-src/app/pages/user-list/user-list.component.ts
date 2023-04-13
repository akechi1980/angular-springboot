import { Component } from '@angular/core';
import { UsersService } from './users.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent {

  data: any = {};

  constructor(private mUsersService: UsersService) { }

  ngOnInit(): void {
    this.mUsersService.getData().subscribe(data => {
      console.log(data);
      this.data = data;
    });
  }

}
