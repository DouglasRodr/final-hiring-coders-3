import { OAuth } from "../@types";
import Service from "../Service";

const clientId = "controll-store-3URPIH";
const secret =
  "5B0OB2UM4OH26ZXV0DJFKSAMII5DT8RSRAEDXP2RJLQY21KMDZNACQ596M458A1HDGQLO95BPHJTDLXPSIHRQR2JN7GXG633DILMAJKLL4Z0SVX8P63NS6QCBO0A2FWI";

class OAuthService extends Service {
  static getAuthorizationToken() {
    const basicAuth = Buffer.from(`${clientId}:${secret}`).toString("base64");
    return this.Http.post<OAuth.Token>(
      "/oauth/token",
      "grant_type=client_credentials&scope=STORE",
      {
        headers: {
          "Content-type": "application/x-www-form-urlencoded",
          Accept: "application/json, text/plain, */*",
          Authorization: `Basic ${basicAuth}`,
        },
      }
    ).then(this.getData);
  }
}

export default OAuthService;
