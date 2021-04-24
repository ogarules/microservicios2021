import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotesListComponent } from './notes-list/notes-list.component';
import { NotesComponent } from './notes/notes.component';

const routes: Routes = [
  {path: '', redirectTo: 'notes', pathMatch: 'full'},
  {path: 'notes', component: NotesListComponent},
  {path: 'notes/:id', component: NotesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
