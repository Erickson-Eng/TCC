import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { TabRoutes } from '../../../shared/component/tab/model/tabRoutes';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.scss'],
})
export class PersonalComponent implements OnInit {
  rota = 'Profile info';
  moduleRoutes: Route[] = [];
  acceptedTypes: string[] = ['image/jpeg', 'image/png'];
  selectedFile: File | null = null;

  routes: TabRoutes[] = [
    { name: 'personal', route: '/profile/personal' },
    { name: 'address', route: '/profile/address' },
    { name: 'athletic', route: '/profile/athletic' },
  ];

  meuFormulario = new FormGroup({
    name: new FormControl(''),
    cpf: new FormControl(''),
    socialName: new FormControl(''),
    socialPronoun: new FormControl(''),
    birthday: new FormControl(''),
    bio: new FormControl(''),
  })

  constructor(private router: Router) {}

  ngOnInit() {}

  onFileSelected(file: File) {
    this.selectedFile = file;
    console.log(this.selectedFile);
  }

  onSubmit(){
    console.log(this.meuFormulario.value)
  }
}
