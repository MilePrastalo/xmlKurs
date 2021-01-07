import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateFakultetComponent } from './create-fakultet/create-fakultet.component';


const routes: Routes = [{path:"",component:CreateFakultetComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
