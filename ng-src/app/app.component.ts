import { Component } from '@angular/core';
import { environment } from './../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent {
  title = '!!Test!!';

  constructor() {
    console.log(environment.production); // Logs false for development environment
  }

}
