import { Component } from '@angular/core';
import { UserService } from '../Services/user/user.service';
import { PaisService } from '../Services/pais/pais.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-competicion',
  templateUrl: './competicion.component.html',
  styleUrls: ['./competicion.component.css']
})
export class CompeticionComponent {
  finalizarForm: FormGroup;
  paises: any[] = [];

  constructor(private fb: FormBuilder, private userService: UserService, private paisService: PaisService, private toastr: ToastrService ) {
    this.finalizarForm = this.fb.group({
      campeon: ['', [Validators.required]],
      subCampeon: ['', [Validators.required]],
    });
   }

  ngOnInit() {
    this.loadPaises();
  }

  loadPaises() {
    this.paisService.getPaises().subscribe((data: any[]) => {
      this.paises = data;
    });
  }

  finalizar() {
    const { campeon, subCampeon } = this.finalizarForm.value;
    this.userService.finalizarEvento(campeon, subCampeon).subscribe((data) => {
      console.log('Evento finalizado');
      this.toastr.success('Evento finalizado'); // Show success toast
    });
    console.log('Competicion finalizada');
  }
}
