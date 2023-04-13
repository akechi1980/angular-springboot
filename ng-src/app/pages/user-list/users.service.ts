import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getData(): Observable<any[]> {
    return this.http.get<any>('https://akechi1980-curly-tribble-pj9q6r7pjxrh6vx9-8080.preview.app.github.dev/api/users');
  }

}
