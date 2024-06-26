import { Component } from '@angular/core';
import { UserService } from '../Services/user/user.service';
import { PrediccionService } from '../Services/prediccion/prediccion.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  user : any = { };
  modify: boolean = false;

  constructor(private userService: UserService, private prediccionService: PrediccionService) { }
  toggleModify() {
    this.modify = !this.modify;
    console.log('Modify: ', this.modify);
  }

  ngOnInit() {
    this.getUser();
  }

  modifyUser() {
    console.log('User modified');
  }

  logout() {
    localStorage.removeItem('id_user');
    localStorage.removeItem('token');
  }

  getUser() {
    const id_user = localStorage.getItem('id_user');
    if (!id_user) {
      return;
    }
    this.userService.getUserById(id_user).subscribe((data) => {
      this.user.cedula = data.cedula;
      this.user.nombre = data.nombre;
      this.user.apellido = data.apellido;
      this.user.email = data.email;
    });
  }
}
