import { Products } from "../@types";
import Service from "../Service";
import OAuthService from "./OAuth.service";

class SuggestionService extends Service {
  static async getSuggestionsByProductId(productId: number) {
    const token = await OAuthService.getAuthorizationToken();

    return this.Http.get<Products.Suggestion[]>(
      `/v1/products/${productId}/suggestions`,
      {
        headers: {
          Accept: "application/json",
          Authorization: `Bearer ${token.access_token}`,
        },
      }
    ).then(this.getData);
  }
}

export default SuggestionService;
