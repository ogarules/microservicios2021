package com.example.demo.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Greeting {

    private String message;
}


//  public class Greeting {
    
//      private String message;

//      public String getMessage() {
//          return message;
//      }

//      public void setMessage(String message) {
//          this.message = message;
//      }

//     @Override
//     public String toString() {
//         return "Greeting [message=" + message + "]";
//     }

//     @Override
//     public int hashCode() {
//         final int prime = 31;
//         int result = 1;
//         result = prime * result + ((message == null) ? 0 : message.hashCode());
//         return result;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj)
//             return true;
//         if (obj == null)
//             return false;
//         if (getClass() != obj.getClass())
//             return false;
//         Greeting other = (Greeting) obj;
//         if (message == null) {
//             if (other.message != null)
//                 return false;
//         } else if (!message.equals(other.message))
//             return false;
//         return true;
//     }

//     public Greeting() {
//     }

//     public Greeting(String message) {
//         this.message = message;
//     }
//  }
