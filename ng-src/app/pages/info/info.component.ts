import { Component } from '@angular/core';
import { InfoService } from './info.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html'
})
export class InfoComponent {

  data: any;

  constructor(private mInfoService: InfoService) { }

  ngOnInit(): void {
    this.mInfoService.getData().subscribe(data => {
      console.log(data);
      this.data = data;
    });
  }

}
